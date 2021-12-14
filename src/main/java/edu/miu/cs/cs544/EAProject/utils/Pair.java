package edu.miu.cs.cs544.EAProject.utils;

import lombok.Getter;

@Getter
public class Pair<S,T> {

    private final S left;
    private final T right;

    public Pair(S left, T right) {
        this.left = left;
        this.right = right;
    }
}
