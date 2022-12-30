package api;


import lombok.ToString;

@ToString
public enum CampaignFieldMeta {
    CampaignName("fields[0].label"),
    CampaignIsMandatory("fields[0].mandatory"),
    CampaignNameDataType("fields[0].type.name");

    String label;
    CampaignFieldMeta(String label){
        this.label=label;
    }

}
