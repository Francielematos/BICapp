package com.bicapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "questionnaires")
data class QuestionnaireEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    // Identificação do Imóvel
    val qte: String = "",
    val qla: String = "",
    val setor: String = "",
    val quadra: String = "",
    val lote: String = "",
    val unidade: String = "",
    val qteAnterior: String = "",
    val qlaAnterior: String = "",
    val setorAnterior: String = "",
    val quadraAnterior: String = "",
    val loteAnterior: String = "",
    val unidadeAnterior: String = "",
    
    // Endereço
    val codigoBairro: String = "",
    val bairro: String = "",
    val codigoLogradouro: String = "",
    val logradouro: String = "",
    val numeroAntigo: String = "",
    val numeroSMN: String = "",
    val complemento: String = "",
    val quadraEndereco: String = "",
    val loteEndereco: String = "",
    val cep: String = "",
    
    // Contribuinte
    val cpfCnpj: String = "",
    
    // Croqui
    val croquiAnexo: Boolean = false,
    val testada: String = "",
    val areaTerreno: String = "",
    val areaUnidade: String = "",
    val areaConstTotal: String = "",
    val escala: String = "",
    
    // Alvará
    val numeroAlvara: String = "",
    val descricaoImovel: String = "",
    val dataPA: String = "",
    val dataVist: String = "",
    val areaTerrenoM2: String = "",
    val areaConstExistente: String = "",
    val areaConstAcrescimo: String = "",
    val areaDemolida: String = "",
    val areaConstruidaTotal: String = "",
    val dataHabiteSe: String = "",
    val areaHabiteSe: String = "",
    
    // Redes e Serviços
    val redeAgua: Boolean = false,
    val redeEsgoto: Boolean = false,
    val redeEletrica: Boolean = false,
    val redeTelefonica: Boolean = false,
    val meioFio: Boolean = false,
    val sarjeta: Boolean = false,
    val pavimentacao: Boolean = false,
    val galeriaPluvial: Boolean = false,
    val coletaLixo: Boolean = false,
    val limpezaPublica: Boolean = false,
    
    // Logradouro
    val larguraLogradouro: String = "",
    val larguraPasseio: String = "",
    
    // Situação do Terreno
    val situacaoTerreno: String = "", // MEIO_QUADRA, ESQUINA, FUNDO, etc.
    
    // Ligações
    val ligacaoAgua: Boolean = false,
    val ligacaoEsgoto: Boolean = false,
    val ligacaoEletrica: Boolean = false,
    val ligacaoTelefonica: Boolean = false,
    val muro: Boolean = false,
    val passeio: Boolean = false,
    
    // Regime de Utilização
    val regimeUtilizacao: String = "", // PROPRIO, ALUGADO, CEDIDO, INVASAO
    
    // Tipo de Ocupação
    val tipoOcupacao: String = "", // CONSTRUIDO, EM_CONSTRUCAO, EM_RUINA, DEMOLIDO
    
    // Patrimônio
    val patrimonio: String = "", // PARTICULAR, PUBLICO, RELIGIOSO, HISTORICO, TOMBADO
    
    // Dimensões e Características do Terreno
    val dimensoesFrentes: String = "", // REGULAR, IRREGULAR
    val quantidadeFrentes: String = "",
    val tipoTopografico: String = "", // PLANO, ACLIVE, DECLIVE
    val tipoPedologico: String = "", // NORMAL, BREJO_ALAGADO, INUNDAVEL, ROCHOSO
    val testadaTerreno: String = "",
    val areaLegalTerreno: String = "",
    
    // Localização da Edificação
    val localizacaoEdificacao: String = "", // FRENTE, FUNDO
    
    // Edificação
    val tipoEdificacao: String = "", // CASA, SOBRADO, PREDIO, COMERCIO, GALPAO, TELHEIRO
    
    // Situação da Edificação
    val situacaoEdificacao: String = "", // ALINHADA, RECUADA
    val posicionamentoEdificacao: String = "", // ISOLADA, GEMINADA, SUPERPOSTA, CONJUGADA
    
    // Equipamentos
    val quadraEsportiva: Boolean = false,
    val piscina: Boolean = false,
    val interfone: Boolean = false,
    val sauna: Boolean = false,
    val hidromassagem: Boolean = false,
    val aquecedor: Boolean = false,
    val elevador: Boolean = false,
    
    // Estrutura
    val estrutura: String = "", // ALVENARIA, CONCRETO, MADEIRA, METALICA
    
    // Cobertura
    val cobertura: String = "", // AMIANTO, LAJE, CERAMICA, METALICA
    
    // Esquadria
    val esquadria: String = "", // SEM, FERRO, MADEIRA, ALUMINIO, ESPECIAL
    
    // Atividade
    val atividade: String = "", // RESIDENCIAL, COMERCIAL, GALPAO, TELHEIRO, RELIGIOSO
    
    // Localização da Unidade
    val localizacaoUnidade: String = "", // SUB_SOLO, SOBRELOJA, GALERIA, COBERTURA
    
    // Revestimento/Acabamento
    val revestimentoExterno: String = "", // SEM, REBOCO_SEM_PINTURA, REBOCO_COM_PINTURA, CERAMICO, ESPECIAL
    val revestimentoInterno: String = "", // PINTURA
    val piso: String = "", // SEM, CIMENTO, TACO_PAVIFLEX, CERAMICO, ESPECIAL
    val forro: String = "", // SEM, LAJE, GESSO, MADEIRA_PVC, ESPECIAL
    
    // Instalação Sanitária
    val instSanitariaExterna: String = "", // SEM, SIMPLES, COMPLETA
    val qtdInstSanitariaExterna: String = "",
    val instSanitariaInterna: String = "", // SEM, SIMPLES, COMPLETA
    val qtdInstSanitariaInterna: String = "",
    
    // Instalação Elétrica
    val instalacaoEletrica: String = "", // SEM, EMBUTIDA, SEMI_EMBUTIDA, APARENTE
    
    // Conservação
    val conservacao: String = "", // NOVA, BOA, REGULAR, PESSIMA
    
    // Quantidades
    val qtdPavimentos: String = "",
    val qtdUnidades: String = "",
    val areaUnidadeM2: String = "",
    val areaConstruidaM2: String = "",
    val areaLegalUnidade: String = "",
    val areaLegalConstrucao: String = "",
    val nomeEdificio: String = "",
    
    // Observações
    val observacoes: String = "",
    
    // Metadados
    val dataPreenchimento: Date = Date(),
    val vistoriador: String = "",
    val equipe: String = "",
    val isCompleted: Boolean = false,
    val pdfPath: String? = null
)

