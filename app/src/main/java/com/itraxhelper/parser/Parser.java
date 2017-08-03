package com.itraxhelper.parser;


import com.itraxhelper.models.Model;

/**
 * Created by Shankar Rao on 3/28/2016.
 */
public interface Parser<T extends Model> {

    T parse(String s);
}