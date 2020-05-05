package com.capstone.sheepsheadbackend.model.response;

import com.capstone.sheepsheadbackend.model.Card;
import com.google.gson.Gson;

import java.util.List;

public class AcceptBlindResponse extends AbstractResponse{
    public String getPickerName() {
        return pickerName;
    }

    public void setPickerName(String pickerName) {
        this.pickerName = pickerName;
    }

    String pickerName;

    public AcceptBlindResponse(String playerId, String gameId, String pickerName){
        super(playerId, gameId, "blindAccepted");
        this.pickerName = pickerName;
    }

    @Override
    public String createResponse() {
        return new Gson().toJson(this);
    }
}
