package com.codereanimator.scrum.documentation.export;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import word.w2004.Document2004;
import word.w2004.elements.BreakLine;
import word.w2004.elements.Heading1;
import word.w2004.elements.Heading2;
import word.w2004.elements.Paragraph;

import com.codereanimator.scrum.documentation.model.Story;


public class WordExporter {
	
	
	/**
	 * Exports the test cases in the given story to an MS Word document.
	 * 
	 * @param story
	 * @param outputStream
	 * @throws Exception
	 */
	public void export(List<Story> stories, OutputStream outputStream) throws Exception {
		Document2004 document = new Document2004();
		
		for(Story story : stories){
			document.getBody().addEle(Heading1.with("Story " + story.getNumber() + ": " + story.getDescription()));
		
			for(com.codereanimator.scrum.documentation.model.Test test : story.getTests()){
				document.getBody().addEle(Heading2.with(test.getName() + " (" + test.getType().toString() + ")")  );
				
				for(String s : test.getDescription().split("\\n")){
					if(StringUtils.isNotBlank(s)){
						document.getBody().addEle(Paragraph.with(s));
						document.getBody().addEle(BreakLine.times(1));
					}
				}
			}
		}
		PrintWriter writer = new PrintWriter(outputStream);
		writer.print(document.getContent());
		writer.close();
	}

}
