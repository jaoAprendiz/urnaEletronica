/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author labsfiap
 */
public class UrnaController {
    private static boolean votoEmBranco = false;
    
    public static void adicionarNumero(String numero) {
        String atual = View.telaVotacao.getNumeroEscolhido();
        if (atual.length() < 2) {
            View.telaVotacao.setNumeroEscolhido(atual + numero);

            // Atualiza nome do candidato ao digitar
            try {
                int numeroInt = Integer.parseInt(View.telaVotacao.getNumeroEscolhido());
                String candidato = Models.UrnaModel.getCandidato(numeroInt);
                if (candidato != null) {
                    View.telaVotacao.setNomeCandidato(candidato);
                    
                    String caminhoImagem = "src/Images/candidato" + numeroInt + ".png";
                    View.telaVotacao.fotoCandidato.setIcon(new ImageIcon(caminhoImagem));
                } else {
                    View.telaVotacao.setNomeCandidato("Número inválido");
                    
                    String caminhoImagem = "src/Images/invalido.png";
                    View.telaVotacao.fotoCandidato.setIcon(new ImageIcon(caminhoImagem));
                }
            } catch (NumberFormatException e) {
                View.telaVotacao.setNomeCandidato("");
            }
        }
    }
    
    public static void corrigirVoto() {
        View.telaVotacao.setNumeroEscolhido("");
        View.telaVotacao.setNomeCandidato("");
        View.telaVotacao.fotoCandidato.setIcon(null);
    }
    
    public static void confirmarVoto() {
        String numeroDigitado = View.telaVotacao.getNumeroEscolhido();

        // 1. Se foi em branco
        if (votoEmBranco) {
            Models.UrnaModel.registrarVoto("BRANCO", true);
            JOptionPane.showMessageDialog(null, "Voto em BRANCO confirmado!");
            votoEmBranco = false;
            corrigirVoto();
            return;
        }

        // 2. Se for nulo explícito
        if ("00".equals(numeroDigitado)) {
            Models.UrnaModel.registrarVoto("NULO", false);
            JOptionPane.showMessageDialog(null, "Voto NULO confirmado!");
            corrigirVoto();
            return;
        }

        // 3. Se for inválido (não existe candidato)
        try {
            int numeroInt = Integer.parseInt(numeroDigitado);
            String candidato = Models.UrnaModel.getCandidato(numeroInt);

            if (candidato == null) {
                Models.UrnaModel.registrarVoto("NULO", false);
                JOptionPane.showMessageDialog(null, "Número inválido! Voto registrado como NULO.");
                corrigirVoto();
                return;
            }

            // 4. Se for válido
            Models.UrnaModel.registrarVoto(numeroDigitado, false);
            JOptionPane.showMessageDialog(null, "Voto confirmado para " + candidato + " (" + numeroDigitado + ")");
            corrigirVoto();

        } catch (NumberFormatException e) {
            Models.UrnaModel.registrarVoto("NULO", false);
            JOptionPane.showMessageDialog(null, "Entrada inválida! Voto registrado como NULO.");
            corrigirVoto();
        }
    }
    
    public static void votarEmBranco() {        
        votoEmBranco = true;
        View.telaVotacao.setNumeroEscolhido(""); 
        View.telaVotacao.setNomeCandidato("VOTO EM BRANCO");
    }
    
    public static void encerrarVotacao() {
        StringBuilder resultado = new StringBuilder("RESULTADOS:\n");

        Models.UrnaModel.getResultados().forEach((numero, qtd) -> {
            resultado.append(numero)
                    .append(" - ").append(Models.UrnaModel.getCandidato(numero))
                    .append(": ").append(qtd).append(" votos\n");
        });

        resultado.append("Brancos: ").append(Models.UrnaModel.getVotosBrancos()).append("\n");
        resultado.append("Nulos: ").append(Models.UrnaModel.getVotosNulos()).append("\n");
        resultado.append("Vencedor: ").append(Models.UrnaModel.getVencedor());

        JOptionPane.showMessageDialog(null, resultado.toString(), "Resultado Final", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}

