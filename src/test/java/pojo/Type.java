package pojo;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Type {
    public String name;
    public ArrayList<PicklistValue> picklistValues;
    public String defaultValue;
    public ArrayList<String> refersTo;
    public String format;
}
