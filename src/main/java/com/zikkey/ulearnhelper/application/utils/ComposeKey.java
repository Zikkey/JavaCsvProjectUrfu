package com.zikkey.ulearnhelper.application.utils;

public class ComposeKey<T1, T2> {
    public final T1 value1;
    public final T2 value2;

    public ComposeKey(T1 value1, T2 value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        var other = (ComposeKey<T1, T2>) obj;
        return value1.equals(other.value1) && value2.equals(other.value2);
    }

    @Override
    public int hashCode() {
        var result = value1 != null ? value1.hashCode() : 0;
        result = 31 * result + (value2 != null ? value2.hashCode() : 0);
        return result;
    }
}
