package com.zikkey.ulearnhelper.application.services;

import com.zikkey.ulearnhelper.domain.entities.Course;
import com.zikkey.ulearnhelper.domain.entities.Exercise;
import com.zikkey.ulearnhelper.domain.entities.Module;
import com.zikkey.ulearnhelper.domain.entities.Student;
import com.zikkey.ulearnhelper.domain.entities.StudentScore;
import com.zikkey.ulearnhelper.domain.enums.ExerciseType;
import lombok.extern.log4j.Log4j2;
import org.knowm.xchart.*;
import org.knowm.xchart.style.AxesChartStyler;
import org.knowm.xchart.style.markers.Marker;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.*;
import java.util.List;

@Service
@Log4j2
public class ChartService {
    public void createAndSaveChart(String path, List<String> info) {
        PieChart sexChart = getSexChart(info);
        CategoryChart cityChart = getCityChart(info);

        try {
            BitmapEncoder.saveBitmapWithDPI(cityChart, path, BitmapEncoder.BitmapFormat.PNG, 300);
        } catch(Exception e) {
            return;
        }
        
    }


    public void createPerformanceChart(String path, Course course, List<Student> students) {
        var chart = getHomeworkChart(course, students);
        try {
            BitmapEncoder.saveBitmapWithDPI(chart, path, BitmapEncoder.BitmapFormat.PNG, 300);
        } catch(Exception e) {
            return;
        }
    }

    private static PieChart getStudentsChart(Course course, List<Student> students) {
        var maxScore = 0;
        for (Module module : course.getModules()) {
            for (Exercise exercise : module.getExercises()) {
                maxScore += exercise.getThresholdScore();
            }
        }
        Map<String, Integer> grades = new HashMap<>();
        for (Student student : students) {
            var score = 0;
            for (StudentScore studentScore : student.getStudentScores()) {
                score += studentScore.getScore();
            }
            var p = (double)score / maxScore;

            if (p < 0.4) {
                grades.put("2", grades.getOrDefault("2", 0) + 1);
            } else if (p < 0.6) {
                grades.put("3", grades.getOrDefault("3", 0) + 1);
            } else if (p < 0.8) {
                grades.put("4", grades.getOrDefault("4", 0) + 1);
            } else {
                grades.put("5", grades.getOrDefault("5", 0) + 1);
            }
        }
        var builder = new PieChartBuilder();
        var chart = builder.title("Статистика студентов по успеваемости").build();
        grades.forEach(chart::addSeries);
        return chart;
    }

    private static PieChart getComfortAndSportChart(Course course, List<Student> students) {
        var maxScore = 0;
        for (Module module : course.getModules()) {
            for (Exercise exercise : module.getExercises()) {
                if(exercise.GetType().equals(ExerciseType.Homework)) {
                    maxScore += exercise.getThresholdScore();
                }
            }
        }
        Map<String, Integer> grades = new HashMap<>();
        int comfort5 = 0;
        int sport5 = 0;
        for (Student student : students) {
            var score = 0;
            for (StudentScore studentScore : student.getStudentScores()) {
                score += studentScore.getScore();
            }
            var p = (double)score / maxScore;

            String grade;
            if (p < 0.4) {
                continue;
            } else if (p < 0.6) {
                continue;
            } else if (p < 0.8) {
                continue;
            } else {
                grade = "5";
                String group = student.getGroup();
                if (group.contains("У1")) {
                    comfort5++;
                } else if (group.contains("У2")) {
                    sport5++;
                }
            }

        }
        var builder = new PieChartBuilder();
        var chart = builder.title("Статистика студентов по оценке 5 в группах комфорт/спорт").build();
        chart.addSeries("Комфорт, оценка - 5", comfort5);
        chart.addSeries("Спорт, оценка - 5", sport5);
        return chart;
    }

    private static CategoryChart getHomeworkChart(Course course, List<Student> students) {
        var maxScore = 0;
        for (Module module : course.getModules()) {
            for (Exercise exercise : module.getExercises()) {
               if(exercise.GetType().equals(ExerciseType.Homework)) {
                    maxScore += exercise.getThresholdScore();
                }
            }
        }
        int totalScores = 0;
        for (Student student : students) {
            var score = 0;
            for (StudentScore studentScore : student.getStudentScores()) {
                Exercise exercise = studentScore.getExercise();
                if(exercise.GetType().equals(ExerciseType.Homework)) {
                    score += studentScore.getScore();
                }
            }
            totalScores += score;
        }
        int avgScore = totalScores / students.size();

        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("Статистика студентов по баллам за Домашние работы").xAxisTitle("Баллы").yAxisTitle("Число баллов").build();
        chart.addSeries("Максимальный балл", new int[] {maxScore}, new int[] {maxScore});
        chart.addSeries("Средний балл", new int[] {avgScore}, new int[] {avgScore} );
        chart.getStyler().setSeriesColors(new Color[]{Color.red, Color.blue});
        return chart;
    }

    private static CategoryChart getCityChart(List<String> info) {
        var dict = new HashMap<String, Integer>();
        info.forEach(x -> {
            if (x != null) {
                if (!dict.containsKey(x))
                    dict.put(x, 1);
                dict.put(x, dict.get(x) + 1);
            }
        });
        List<Map.Entry<String, Integer>> list = new ArrayList<>(dict.entrySet());
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        List<Map.Entry<String, Integer>> topFive = list.subList(0, 5);
        var sortedDict = new HashMap<String, Integer>();
        topFive.forEach(x -> sortedDict.put(x.getKey(), x.getValue()));
        var builder = new CategoryChartBuilder();
        var chart = builder.title("Статистика студентов по городам")
                .yAxisTitle("Количество студентов")
                .xAxisTitle("Города")
                .width(1500)
                .height(500)
                .build();
        chart.getStyler().getYAxisGroupPosistion(5);
        chart.addSeries("Город", sortedDict.keySet().stream().toList(), sortedDict.values().stream().toList());
        return chart;
    }

    private static PieChart getSexChart(List<String> sex) {
        var builder = new PieChartBuilder();
        var chart = builder.title("Статистика студентов по полу").build();
        var men = new ArrayList<Integer>();
        var women = new ArrayList<Integer>();
        sex.forEach(x -> {
            if(x != null) {
                if (x.equals("2"))
                    men.add(Integer.valueOf(x));
                if (x.equals("1"))
                    women.add(Integer.valueOf(x));
            }
        });
        chart.addSeries("Мужчины", men.size());
        chart.addSeries("Женщины", women.size());
        return chart;
    }
}
