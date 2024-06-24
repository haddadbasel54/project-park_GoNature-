package GoNature;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.rendering.PDFRenderer;

/**
 * PDFcreator is a utility class for creating PDF files with custom content. It
 * provides a method to generate a PDF file with the given text content and save
 * it to a specified location. Additionally, it supports converting the
 * generated PDF to a JPEG image for preview purposes.
 *
 * This class uses Apache PDFBox library for PDF manipulation.
 *
 * @author Pier Mbariky
 * @author Redan Ganim
 */
public class PDFcreator {
	/**
	 * Creates a PDF file with the given text content and saves it to the specified
	 * location. Additionally, converts the generated PDF to a JPEG image for
	 * preview purposes.
	 *
	 * @param text The text content to be included in the PDF.
	 * @return The file path of the generated JPEG image.
	 * @throws IOException If an I/O error occurs.
	 */
	public static String createPDF(String text) throws IOException {
		// Get the current project directory
		String projectDirectory = System.getProperty("user.dir");
		// Create a folder named PDFCREATED if it doesn't exist
		File pdfFolder = new File(projectDirectory, "PDFCREATED");
		pdfFolder.mkdirs(); // Create folder if it doesn't exist
		// Generate a unique filename for the PDF and JPEG files
		String filename = "reciept" + System.currentTimeMillis() + ".pdf";
		String filePath = new File(pdfFolder, filename).getAbsolutePath();
		// Create a new PDF document and page

		PDDocument document = new PDDocument();
		PDPage page = new PDPage(new PDRectangle(PDRectangle.A6.getHeight(), PDRectangle.A6.getWidth()));
		document.addPage(page);
		// Initialize a PDPageContentStream for adding content to the page
		PDPageContentStream contentStream = new PDPageContentStream(document, page);
		// Define page dimensions and line height for better readability
		float pageWidth = 105 * (72 / 25.4f);
		float pageHeight = 145 * (72 / 25.4f);
		float middle = pageHeight / 2;
		float lineHeight = 15; // Define line height for better readability

		// Set font and font size for title
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 18);

		// Add title to the PDF
		contentStream.beginText();
		contentStream.newLineAtOffset(pageWidth / 2, middle);
		contentStream.showText("Park Admission");
		contentStream.endText();

		// Set font and font size for content
		contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 14);
		String[] lines = text.split("\n");

		// Adjust Y offset for initial content position
		middle -= lineHeight * 2;

		// Iterate over each line and add it to the PDF
		for (String line : lines) {
			contentStream.beginText();
			contentStream.newLineAtOffset((pageWidth / 2), middle);
			contentStream.showText(line);
			contentStream.endText();

			// Update the Y offset for the next line
			middle -= lineHeight; // Adjusted based on line spacing
		}
		// Close the content stream
		contentStream.close();
		// Render the PDF as a JPEG image for preview
		PDFRenderer renderer = new PDFRenderer(document);
		BufferedImage bufferedImage = renderer.renderImageWithDPI(0, 300);
		String jpgFilename = "receipt" + System.currentTimeMillis() + ".jpg";
		String jpgFilePath = new File(pdfFolder, jpgFilename).getAbsolutePath();
		ImageIO.write(bufferedImage, "jpg", new File(jpgFilePath));
		// Close the PDF document
		document.close();
		new File(filePath).delete();
		// Return the file path of the generated JPEG image
		return jpgFilePath;
	}

}