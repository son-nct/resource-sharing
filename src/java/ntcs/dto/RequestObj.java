/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ntcs.dto;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author WIN 10
 */
public class RequestObj {

    private Map<RequestResourceDTO, Integer> items;

    public Map<RequestResourceDTO, Integer> getItems() {
        return items;
    }

    public void addRequest(RequestResourceDTO resourceDTO) {
        if (items == null) {
            items = new HashMap<>();
        }

        int quantity = 1;

        for (RequestResourceDTO dto : items.keySet()) {
            if (dto.getIdResource().equalsIgnoreCase(resourceDTO.getIdResource()) && dto.getRentalDate().equals(resourceDTO.getRentalDate())) {
                quantity = items.get(dto) + 1;
                items.remove(dto);
                break;
            }
        }
        items.put(resourceDTO, quantity);

    }

    public void deleteRequest(String idResource, String date) {
        if(items == null) {
            return;
        }
        
        for (RequestResourceDTO dto : items.keySet()) {
            if (dto.getIdResource().equalsIgnoreCase(idResource) && dto.getRentalDate().equals(date)) {
                items.remove(dto);
                break;
            }
        }
        
        if(this.items.isEmpty()) {
            this.items = null;
        }
    }

}
