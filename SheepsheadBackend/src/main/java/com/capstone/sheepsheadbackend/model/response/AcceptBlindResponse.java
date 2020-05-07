package com.capstone.sheepsheadbackend.model.response;

import com.capstone.sheepsheadbackend.model.Card;
import com.google.gson.Gson;

import java.util.List;

public class AcceptBlindResponse extends AbstractResponse{

    String pickerName;

    /**
     *
     * @param playerId
     * @param gameId
     * @param pickerName
     */
    public AcceptBlindResponse(String playerId, String gameId, String pickerName){
        super(playerId, gameId, "blindAccepted");
        this.pickerName = pickerName;
    }

    @Override
    public String createResponse() {
        return new Gson().toJson(this);
    }

    /**
     *
     * @return
     */
    public String getPickerName() {
        return pickerName;
    }

    /**
     *
     * @param pickerName
     */
    public void setPickerName(String pickerName) {
        this.pickerName = pickerName;
    }
}
