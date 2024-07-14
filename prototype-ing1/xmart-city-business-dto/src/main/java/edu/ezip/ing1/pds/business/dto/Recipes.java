package edu.ezip.ing1.pds.business.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashSet;
import java.util.Set;




public class Recipes {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("recipes")
    private Set<Recipe> recettes = new LinkedHashSet<>();

    public Set<Recipe> getRecettes() {
        return recettes;
    }

    @JsonProperty("recipes")
    public void setRecettes(Set<Recipe> recettes) {
        this.recettes = recettes;
    }

    public final Recipes add(final Recipe recette) {
        recettes.add(recette);
        return this;
    }

    @Override
    public String toString() {
        return "Recipes{" +
                "recipes=" + recettes +
                '}';
    }
}
