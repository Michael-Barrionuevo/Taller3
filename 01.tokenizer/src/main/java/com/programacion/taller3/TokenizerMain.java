package com.programacion.taller3;

import com.knuddels.jtokkit.Encodings;
import com.knuddels.jtokkit.api.Encoding;
import com.knuddels.jtokkit.api.EncodingRegistry;
import com.knuddels.jtokkit.api.IntArrayList;
import com.knuddels.jtokkit.api.ModelType;

public class TokenizerMain {
    static void main() {

        EncodingRegistry registry = Encodings.newDefaultEncodingRegistry();
        Encoding tokenizer = registry.getEncodingForModel(ModelType.TEXT_DAVINCI_003);
        //Encoding tokenizer2 = registry.getEncodingForModel(ModelType.TEXT_DAVINCI_001);

        var text = "Hello, do you like tea someunknowPlace?";

        // Para encode
        IntArrayList tokenIds = tokenizer.encode(text);

        System.out.println("Total tokens: " + tokenIds.size());
        System.out.println(tokenIds);
        //System.out.println(tokenizer2.encode(text));

        // Decode
        String textDecoded = tokenizer.decode(tokenIds);
        System.out.println("Decoded: " +textDecoded);



    }

}
