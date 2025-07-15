package com.bicapp.pdf

import android.content.Context
import com.bicapp.data.entity.QuestionnaireEntity
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Table
import com.itextpdf.layout.property.TextAlignment
import com.itextpdf.layout.property.UnitValue
import com.itextpdf.kernel.font.PdfFont
import com.itextpdf.kernel.font.PdfFontFactory
import com.itextpdf.io.font.constants.StandardFonts
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class PDFGenerator(private val context: Context) {
    
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    
    suspend fun generatePDF(questionnaire: QuestionnaireEntity): String {
        val fileName = "BIC_${questionnaire.id}_${System.currentTimeMillis()}.pdf"
        val file = File(context.getExternalFilesDir(null), fileName)
        
        val writer = PdfWriter(FileOutputStream(file))
        val pdfDoc = PdfDocument(writer)
        val document = Document(pdfDoc)
        
        try {
            val font = PdfFontFactory.createFont(StandardFonts.HELVETICA)
            val boldFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD)
            
            // Cabeçalho
            addHeader(document, boldFont)
            
            // Identificação do Imóvel
            addIdentificationSection(document, questionnaire, font, boldFont)
            
            // Endereço
            addAddressSection(document, questionnaire, font, boldFont)
            
            // Contribuinte
            addContributorSection(document, questionnaire, font, boldFont)
            
            // Croqui
            addSketchSection(document, questionnaire, font, boldFont)
            
            // Alvará
            addPermitSection(document, questionnaire, font, boldFont)
            
            // Redes e Serviços
            addNetworksSection(document, questionnaire, font, boldFont)
            
            // Características do Terreno
            addTerrainSection(document, questionnaire, font, boldFont)
            
            // Edificação
            addBuildingSection(document, questionnaire, font, boldFont)
            
            // Observações
            addObservationsSection(document, questionnaire, font, boldFont)
            
            // Rodapé
            addFooter(document, questionnaire, font)
            
        } finally {
            document.close()
        }
        
        return file.absolutePath
    }
    
    private fun addHeader(document: Document, boldFont: PdfFont) {
        val header = Paragraph("DEPARTAMENTO DE CADASTRO")
            .setFont(boldFont)
            .setFontSize(14f)
            .setTextAlignment(TextAlignment.CENTER)
            .setMarginBottom(10f)
        document.add(header)
        
        val title = Paragraph("BOLETIM DE INSCRIÇÃO CADASTRAL \"BIC\"")
            .setFont(boldFont)
            .setFontSize(16f)
            .setTextAlignment(TextAlignment.CENTER)
            .setMarginBottom(20f)
        document.add(title)
    }
    
    private fun addIdentificationSection(document: Document, questionnaire: QuestionnaireEntity, font: PdfFont, boldFont: PdfFont) {
        val sectionTitle = Paragraph("IDENTIFICAÇÃO IMOBILIÁRIO ATUAL")
            .setFont(boldFont)
            .setFontSize(12f)
            .setMarginBottom(10f)
        document.add(sectionTitle)
        
        val table = Table(UnitValue.createPercentArray(floatArrayOf(1f, 1f, 1f, 1f, 1f, 1f)))
            .setWidth(UnitValue.createPercentValue(100f))
        
        // Cabeçalho da tabela
        table.addHeaderCell(createCell("QTE", boldFont))
        table.addHeaderCell(createCell("QLA", boldFont))
        table.addHeaderCell(createCell("SETOR", boldFont))
        table.addHeaderCell(createCell("QUADRA", boldFont))
        table.addHeaderCell(createCell("LOTE", boldFont))
        table.addHeaderCell(createCell("UNIDADE", boldFont))
        
        // Dados atuais
        table.addCell(createCell(questionnaire.qte, font))
        table.addCell(createCell(questionnaire.qla, font))
        table.addCell(createCell(questionnaire.setor, font))
        table.addCell(createCell(questionnaire.quadra, font))
        table.addCell(createCell(questionnaire.lote, font))
        table.addCell(createCell(questionnaire.unidade, font))
        
        document.add(table)
        document.add(Paragraph("\n"))
        
        // Dados anteriores se existirem
        if (questionnaire.qteAnterior.isNotEmpty() || questionnaire.qlaAnterior.isNotEmpty()) {
            val anteriorTitle = Paragraph("ANTERIOR")
                .setFont(boldFont)
                .setFontSize(10f)
            document.add(anteriorTitle)
            
            val anteriorTable = Table(UnitValue.createPercentArray(floatArrayOf(1f, 1f, 1f, 1f, 1f, 1f)))
                .setWidth(UnitValue.createPercentValue(100f))
            
            anteriorTable.addCell(createCell(questionnaire.qteAnterior, font))
            anteriorTable.addCell(createCell(questionnaire.qlaAnterior, font))
            anteriorTable.addCell(createCell(questionnaire.setorAnterior, font))
            anteriorTable.addCell(createCell(questionnaire.quadraAnterior, font))
            anteriorTable.addCell(createCell(questionnaire.loteAnterior, font))
            anteriorTable.addCell(createCell(questionnaire.unidadeAnterior, font))
            
            document.add(anteriorTable)
        }
        
        document.add(Paragraph("\n"))
    }
    
    private fun addAddressSection(document: Document, questionnaire: QuestionnaireEntity, font: PdfFont, boldFont: PdfFont) {
        val table = Table(UnitValue.createPercentArray(floatArrayOf(1f, 3f)))
            .setWidth(UnitValue.createPercentValue(100f))
        
        table.addCell(createCell("CÓD. BAIRRO", boldFont))
        table.addCell(createCell(questionnaire.codigoBairro, font))
        
        table.addCell(createCell("BAIRRO", boldFont))
        table.addCell(createCell(questionnaire.bairro, font))
        
        table.addCell(createCell("CÓD. LOGR.", boldFont))
        table.addCell(createCell(questionnaire.codigoLogradouro, font))
        
        table.addCell(createCell("LOGRADOURO", boldFont))
        table.addCell(createCell(questionnaire.logradouro, font))
        
        table.addCell(createCell("Nº. ANTIGO", boldFont))
        table.addCell(createCell(questionnaire.numeroAntigo, font))
        
        table.addCell(createCell("Nº. (S.M.N.)", boldFont))
        table.addCell(createCell(questionnaire.numeroSMN, font))
        
        table.addCell(createCell("COMPLEMENTO", boldFont))
        table.addCell(createCell(questionnaire.complemento, font))
        
        table.addCell(createCell("CEP", boldFont))
        table.addCell(createCell(questionnaire.cep, font))
        
        document.add(table)
        document.add(Paragraph("\n"))
    }
    
    private fun addContributorSection(document: Document, questionnaire: QuestionnaireEntity, font: PdfFont, boldFont: PdfFont) {
        val contributorTitle = Paragraph("CONTRIBUINTE")
            .setFont(boldFont)
            .setFontSize(12f)
        document.add(contributorTitle)
        
        val cpfParagraph = Paragraph("C.P.F. / C.N.P.J.: ${questionnaire.cpfCnpj}")
            .setFont(font)
            .setFontSize(10f)
        document.add(cpfParagraph)
        document.add(Paragraph("\n"))
    }
    
    private fun addSketchSection(document: Document, questionnaire: QuestionnaireEntity, font: PdfFont, boldFont: PdfFont) {
        val sketchTitle = Paragraph("C R O Q U I S")
            .setFont(boldFont)
            .setFontSize(12f)
        document.add(sketchTitle)
        
        val sketchInfo = if (questionnaire.croquiAnexo) "CROQUI EM ANEXO" else "SEM CROQUI"
        val sketchParagraph = Paragraph(sketchInfo)
            .setFont(font)
            .setFontSize(10f)
        document.add(sketchParagraph)
        
        val measurementsTable = Table(UnitValue.createPercentArray(floatArrayOf(1f, 1f, 1f, 1f)))
            .setWidth(UnitValue.createPercentValue(100f))
        
        measurementsTable.addCell(createCell("Testada: ${questionnaire.testada} m", font))
        measurementsTable.addCell(createCell("Área Terreno: ${questionnaire.areaTerreno} m²", font))
        measurementsTable.addCell(createCell("Área Unidade: ${questionnaire.areaUnidade} m²", font))
        measurementsTable.addCell(createCell("Área Const. Total: ${questionnaire.areaConstTotal} m²", font))
        
        document.add(measurementsTable)
        document.add(Paragraph("\n"))
    }
    
    private fun addPermitSection(document: Document, questionnaire: QuestionnaireEntity, font: PdfFont, boldFont: PdfFont) {
        val permitTitle = Paragraph("ALVARÁ")
            .setFont(boldFont)
            .setFontSize(12f)
        document.add(permitTitle)
        
        val permitTable = Table(UnitValue.createPercentArray(floatArrayOf(1f, 2f)))
            .setWidth(UnitValue.createPercentValue(100f))
        
        permitTable.addCell(createCell("ALVARÁ Nº.", boldFont))
        permitTable.addCell(createCell(questionnaire.numeroAlvara, font))
        
        permitTable.addCell(createCell("DESCRIÇÃO DO IMÓVEL", boldFont))
        permitTable.addCell(createCell(questionnaire.descricaoImovel, font))
        
        permitTable.addCell(createCell("PA - DATA", boldFont))
        permitTable.addCell(createCell(questionnaire.dataPA, font))
        
        permitTable.addCell(createCell("VIST. PA - DATA", boldFont))
        permitTable.addCell(createCell(questionnaire.dataVist, font))
        
        document.add(permitTable)
        document.add(Paragraph("\n"))
    }
    
    private fun addNetworksSection(document: Document, questionnaire: QuestionnaireEntity, font: PdfFont, boldFont: PdfFont) {
        val networksTitle = Paragraph("REDES E SERVIÇOS PÚBLICOS")
            .setFont(boldFont)
            .setFontSize(12f)
        document.add(networksTitle)
        
        val networksTable = Table(UnitValue.createPercentArray(floatArrayOf(1f, 1f, 1f, 1f)))
            .setWidth(UnitValue.createPercentValue(100f))
        
        networksTable.addCell(createCell("ÁGUA: ${if (questionnaire.redeAgua) "SIM" else "NÃO"}", font))
        networksTable.addCell(createCell("ESGOTO: ${if (questionnaire.redeEsgoto) "SIM" else "NÃO"}", font))
        networksTable.addCell(createCell("ELÉTRICA: ${if (questionnaire.redeEletrica) "SIM" else "NÃO"}", font))
        networksTable.addCell(createCell("TELEFÔNICA: ${if (questionnaire.redeTelefonica) "SIM" else "NÃO"}", font))
        
        document.add(networksTable)
        document.add(Paragraph("\n"))
    }
    
    private fun addTerrainSection(document: Document, questionnaire: QuestionnaireEntity, font: PdfFont, boldFont: PdfFont) {
        val terrainTitle = Paragraph("SITUAÇÃO DO TERRENO")
            .setFont(boldFont)
            .setFontSize(12f)
        document.add(terrainTitle)
        
        val terrainTable = Table(UnitValue.createPercentArray(floatArrayOf(1f, 2f)))
            .setWidth(UnitValue.createPercentValue(100f))
        
        terrainTable.addCell(createCell("SITUAÇÃO", boldFont))
        terrainTable.addCell(createCell(questionnaire.situacaoTerreno, font))
        
        terrainTable.addCell(createCell("TIPO TOPOGRÁFICO", boldFont))
        terrainTable.addCell(createCell(questionnaire.tipoTopografico, font))
        
        terrainTable.addCell(createCell("TIPO PEDOLÓGICO", boldFont))
        terrainTable.addCell(createCell(questionnaire.tipoPedologico, font))
        
        document.add(terrainTable)
        document.add(Paragraph("\n"))
    }
    
    private fun addBuildingSection(document: Document, questionnaire: QuestionnaireEntity, font: PdfFont, boldFont: PdfFont) {
        val buildingTitle = Paragraph("EDIFICAÇÃO")
            .setFont(boldFont)
            .setFontSize(12f)
        document.add(buildingTitle)
        
        val buildingTable = Table(UnitValue.createPercentArray(floatArrayOf(1f, 2f)))
            .setWidth(UnitValue.createPercentValue(100f))
        
        buildingTable.addCell(createCell("TIPO", boldFont))
        buildingTable.addCell(createCell(questionnaire.tipoEdificacao, font))
        
        buildingTable.addCell(createCell("ESTRUTURA", boldFont))
        buildingTable.addCell(createCell(questionnaire.estrutura, font))
        
        buildingTable.addCell(createCell("COBERTURA", boldFont))
        buildingTable.addCell(createCell(questionnaire.cobertura, font))
        
        buildingTable.addCell(createCell("CONSERVAÇÃO", boldFont))
        buildingTable.addCell(createCell(questionnaire.conservacao, font))
        
        document.add(buildingTable)
        document.add(Paragraph("\n"))
    }
    
    private fun addObservationsSection(document: Document, questionnaire: QuestionnaireEntity, font: PdfFont, boldFont: PdfFont) {
        val observationsTitle = Paragraph("OBSERVAÇÕES")
            .setFont(boldFont)
            .setFontSize(12f)
        document.add(observationsTitle)
        
        val observationsParagraph = Paragraph(questionnaire.observacoes.ifEmpty { "Nenhuma observação." })
            .setFont(font)
            .setFontSize(10f)
        document.add(observationsParagraph)
        document.add(Paragraph("\n"))
    }
    
    private fun addFooter(document: Document, questionnaire: QuestionnaireEntity, font: PdfFont) {
        val footerTable = Table(UnitValue.createPercentArray(floatArrayOf(1f, 1f, 1f)))
            .setWidth(UnitValue.createPercentValue(100f))
        
        footerTable.addCell(createCell("UBERABA-MG", font))
        footerTable.addCell(createCell(dateFormat.format(questionnaire.dataPreenchimento), font))
        footerTable.addCell(createCell("VISTORIADOR(A): ${questionnaire.vistoriador}", font))
        
        document.add(footerTable)
    }
    
    private fun createCell(content: String, font: PdfFont): com.itextpdf.layout.element.Cell {
        return com.itextpdf.layout.element.Cell()
            .add(Paragraph(content).setFont(font).setFontSize(9f))
            .setPadding(4f)
    }
}

