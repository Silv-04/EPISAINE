package edu.ezip.ing1.pds.business.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashSet;
import java.util.Set;

public class Nutritionists {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("nutritionists")
    private Set<Nutritionist> nutritionnistes = new LinkedHashSet<>();

    public Set<Nutritionist> getNutritionnistes() {
        return nutritionnistes;
    }

    @JsonProperty("nutritionists")
    public void setNutritionnistes(Set<Nutritionist> nutritionnistes) {
        this.nutritionnistes = nutritionnistes;
    }

    public final Nutritionists add(final Nutritionist nutritionniste) {
        nutritionnistes.add(nutritionniste);
        return this;
    }

    @Override
    public String toString() {
        return "Nutritionists{" +
                "nutritionists=" + nutritionnistes +
                '}';
    }
}
