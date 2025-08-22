package Models;

import java.util.HashMap;
import java.util.Map;

public class UrnaModel {
    public static Map<Integer, String> candidatos;
    public static Map<Integer, Integer> votos;
    public static int votosBrancos;
    public static int votosNulos;

    // Bloco estático (executa uma única vez quando a classe é carregada)
    static {
        candidatos = new HashMap<>();
        votos = new HashMap<>();

        // Definição fixa dos candidatos
        candidatos.put(10, "João da Silva");
        candidatos.put(20, "Maria de Souza");
        candidatos.put(30, "Pedro Santos");

        // Inicializa contagem de votos
        for (Integer numero : candidatos.keySet()) {
            votos.put(numero, 0);
        }
        votosBrancos = 0;
        votosNulos = 0;
    }

    public static void registrarVoto(String numeroDigitado, boolean branco) {
        if (branco) {
            votosBrancos++;
        } else {
            try {
                int numero = Integer.parseInt(numeroDigitado);

                if (numeroDigitado.equals("00")) {
                    votosNulos++;
                } else if (candidatos.containsKey(numero)) {
                    votos.put(numero, votos.get(numero) + 1);
                } else {
                    votosNulos++;
                }
            } catch (NumberFormatException e) {
                votosNulos++;
            }
        }
    }

    public static Map<Integer, Integer> getResultados() {
        return votos;
    }

    public static int getVotosBrancos() {
        return votosBrancos;
    }

    public static int getVotosNulos() {
        return votosNulos;
    }

    public static String getCandidato(int numero) {
        return candidatos.get(numero);
    }

    public static String getVencedor() {
        int maxVotos = -1;
        String vencedor = "Empate";
        boolean empate = false;

        for (Map.Entry<Integer, Integer> entry : votos.entrySet()) {
            if (entry.getValue() > maxVotos) {
                maxVotos = entry.getValue();
                vencedor = candidatos.get(entry.getKey());
                empate = false;
            } else if (entry.getValue() == maxVotos) {
                empate = true;
            }
        }

        return empate ? "Empate" : vencedor;
    }
}
