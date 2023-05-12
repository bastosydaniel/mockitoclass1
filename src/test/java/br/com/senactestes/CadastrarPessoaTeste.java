package br.com.senactestes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class CadastrarPessoaTeste {

    //1.CRIACAO DE CENARIO
    //1.1.Instrumentacao para o Mock;
    //1.1.1.Insercao do descritor do Mock;
    @Mock
    private ApiDosCorreios apiDosCorreios;
    //1.1.2.Injecao de dependencia da regra ade negocio a ser testada
    @InjectMocks
    private CadastrarPessoa cadastrarPessoa;

    @Test
    void cadastrarPessoa(){
        //1.2.Insercao dos dados a serem Mockados;
        //Dados fictios do objeto que eu estou mockado
        DadosLocalizacao dadosLocalizacao = new DadosLocalizacao("SP","Atibia","Rua Antoni Massoni","Casa","Nova Floresta");

        //1.3.Execucao do mock
        Mockito.when(apiDosCorreios.buscarDadosComBaseNoCep(anyString())).thenReturn(dadosLocalizacao);

        Pessoa daniel = cadastrarPessoa.cadastrarPessoa("daniel","299456897", LocalDate.of(2003,03,04),"299456897");

        //2.EXECUCAO E ANALISE DO TESTE
        DadosLocalizacao enderecoDaniel = daniel.getEndereco();
        String nomeDaniel = daniel.getNome();
        String documentoDaniel = daniel.getDocumento();
        LocalDate nascimentoDaniel = daniel.getNascimento();
        assertEquals(dadosLocalizacao.getBairro(),enderecoDaniel.getBairro());
        assertEquals(dadosLocalizacao.getCidade(),enderecoDaniel.getCidade());
        assertEquals(dadosLocalizacao.getComplemento(),enderecoDaniel.getComplemento());
        assertEquals(dadosLocalizacao.getUf(),enderecoDaniel.getUf());
        assertEquals(dadosLocalizacao.getLogradouro(),enderecoDaniel.getLogradouro());
    }

}
