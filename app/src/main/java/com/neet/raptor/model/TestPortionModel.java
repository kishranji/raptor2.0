package com.neet.raptor.model;

import java.io.Serializable;
import java.util.ArrayList;

public class TestPortionModel implements Serializable {

    public String mTestName = "";
    public String mTestStatus = "";
    public String mTestDate = "";
    public String mTestMonth = "";
    public ArrayList<TestSubject> mTestSubjectList;


    public static class TestSubject {

        public String mSubjectName = "";
        public String mPortion = "";
        public String mPortionId = "";
    }
}
