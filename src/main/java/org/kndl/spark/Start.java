package org.kndl.spark;

import static spark.Spark.get;
import static spark.Spark.post;




public class Start {

    public static void main(String args[]) {
        Dirty dirty = new Dirty();
        dirty.init();
    }

}
