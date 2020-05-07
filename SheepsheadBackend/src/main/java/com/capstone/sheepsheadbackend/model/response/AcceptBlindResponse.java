package com.capstone.sheepsheadbackend.model.response;

import com.capstone.sheepsheadbackend.model.Card;
import com.google.gson.Gson;

import java.util.List;

public class AcceptBlindResponse extends AbstractResponse{

    String pickerName;

    /**
     * Create a new Response to accept the Blind
     * @param playerId Player Id for response
     * @param gameId Game Id for response
     * @param pickerName Username of picker (player who accepted the blind)
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
     * Get the username of the picker
     * @return Username of the picker
     */
    public String getPickerName() {
        return pickerName;
    }

    /**
     * Set the name of the picker to name given
     * @param pickerName Name of the picker to set to
     */
    public void setPickerName(String pickerName) {
        this.pickerName = pickerName;
    }
}
