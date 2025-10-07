package config;

import io.github.cdimascio.dotenv.Dotenv;

public class Environment {

    private static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing() // no rompe si el archivo no existe
            .load();

    public static final String BASE_URL = dotenv.get("BASE_URL", "https://thesimpsonsapi.com/api");
    public static final int TIMEOUT_MS = Integer.parseInt(dotenv.get("TIMEOUT_MS", "5000"));
}

