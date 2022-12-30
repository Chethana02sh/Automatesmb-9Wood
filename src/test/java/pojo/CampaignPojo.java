package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class CampaignPojo{
    public String label;
    public String name;
    public boolean createable;
    public boolean updateable;
    public boolean deleteable;
    public boolean retrieveable;
    public ArrayList<Field> fields;
    public String idPrefix;
    public boolean isEntity;
    public boolean allowDuplicates;
    public String labelFields;
}


