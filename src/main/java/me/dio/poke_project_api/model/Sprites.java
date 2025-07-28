package me.dio.poke_project_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

@Getter
@Setter
@Embeddable
public class Sprites {
    @JsonProperty("front_default")
    // 1. A anotação de 'frontDefault' agora descreve corretamente o que ele é: uma URL.
    @Schema(description = "The direct URL to the default sprite image.", example = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png")
    private String frontDefault;

    /**
     * Campo virtual para exibir a imagem do sprite no Swagger UI.
     * Este campo não é persistido no banco de dados.
     *
     * @return Uma string em formato Markdown para renderizar a imagem.
     */
    @Transient
    // 2. A anotação de 'spritePreview' agora descreve o campo virtual e fornece o exemplo em Markdown.
    // É esta descrição que o Swagger UI irá renderizar.
    @Schema(description = "A markdown-formatted string for rendering the sprite image directly in documentation UIs that support it.",
            example = "![Sprite](https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png)")
    public String getSpritePreview() {
        if (this.frontDefault != null && !this.frontDefault.isEmpty()) {
            return "![Sprite](" + this.frontDefault + ")";
    }
         return "No image available.";
}
 }