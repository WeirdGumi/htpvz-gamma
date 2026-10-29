package net.budoumiao.htpvzy.api.capability;

public interface Sun {
    int get();

    int getMin();

    int getMax();

    void set(int value);

    void setMin(int min);

    void setMax(int max);

    void add(int value);
}
