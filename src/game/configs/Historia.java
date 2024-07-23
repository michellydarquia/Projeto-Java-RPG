package game.configs;

import Estados.GamePanel;
import game.personagens.npc.Npc;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.Timer;

public class Historia {
    GamePanel gp;
    String[] dialogos;
    String[] dialogosMissao;
    Utils utils;
    private Timer timer;
    int dialogoIndex = 0;
    private String mensagemAtual = "";
    private int index = 0;
    private BufferedImage imagem;
    private int andamentoHistoria;
    private boolean isNpcDialog = false;
    private Npc npcAtual;
    private boolean dialogoAtivo;

    public Historia(GamePanel gp) {
        this.gp = gp;
        this.utils = new Utils();
        getImage();
        andamentoHistoria = 0;
        this.dialogoAtivo = false;

    }

    public void draw(Graphics2D g2) {
        if (isNpcDialog) {
            if (dialogoIndex < dialogos.length) {
                utils.drawRetanguloTranslucidoComBordas(g2, 50, 400, 700, 150, 0.9f);
                g2.setColor(Color.white);
                utils.drawMensagem(g2, mensagemAtual);
            } else {
                isNpcDialog = false;
            }
        } else {
            definirDialogosIniciais(g2);
        }
    }

    public void definirDialogosIniciais(Graphics2D g2) {
        dialogos = new String[]{
                "Bem-vindo ao Reino de Dorá, uma terra de paz e\nprosperidade... até agora.",
                "Herói, o reino de Dorá precisa de sua coragem e\nhabilidade. Os goblins das montanhas sombrias\ncomeçaram a atacar nossos vilarejos indefesos.\nPrecisamos de você para enfrentar essa ameaça!!",
                "Escolha seu caminho e prepare-se para uma jornada\nperigosa. Cada caminho oferece suas próprias\nhabilidades e desafios",
        };
        iniciarTimerParaTexto();
        if (dialogoIndex < dialogos.length) {
            g2.drawImage(imagem, 0, 0, imagem.getWidth(), imagem.getHeight(), null);
            utils.drawRetanguloTranslucidoComBordas(g2, 50, 400, 700, 150, 0.9f);
            g2.setColor(Color.white);
            utils.drawMensagem(g2, mensagemAtual);
        }
    }


    public BufferedImage getImage() {
        try {
            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/fundo/castle.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imagem;
    }

    private void iniciarTimerParaTexto() {
        timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (index < dialogos[dialogoIndex].length()) {
                    mensagemAtual += dialogos[dialogoIndex].charAt(index);
                    index++;
                    gp.repaint();
                } else {
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    public void navigateHistoria() {
        dialogoIndex++;
        if (dialogoIndex < dialogos.length) {
            index = 0;
            mensagemAtual = "";
            iniciarTimerParaTexto();
        } else {
            gp.gameState = gp.stateMenuclasses;
            dialogoIndex = 0;
        }
    }


    /////////////////////


    public void iniciarDialogo(Npc npc) {
        this.npcAtual = npc;
        this.dialogos = npc.getDialogos(); // Obtém os diálogos do NPC
        this.dialogoIndex = 0;
        this.mensagemAtual = "";
        this.index = 0;
        this.isNpcDialog = true;
        this.dialogoAtivo = true;
        iniciarTimerParaTexto();
    }

    public void proximoDialogo() {
        if (dialogoIndex < dialogos.length - 1) {
            dialogoIndex++;
            index = 0;
            mensagemAtual = "";
            iniciarTimerParaTexto();
        } else {
            encerrarDialogo();
        }
    }

    public void encerrarDialogo() {
        this.dialogoAtivo = false;
        gp.gameState = gp.statePlay;
        this.isNpcDialog = false;
        gp.play.statePlay = gp.play.stateJogando; // Atualiza o estado para jogar
    }

    public boolean isDialogoAtivo() {
        return dialogoAtivo;
    }

}

