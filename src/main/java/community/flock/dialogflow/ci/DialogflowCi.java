package community.flock.dialogflow.ci;

import java.util.Arrays;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import community.flock.dialogflow.ci.dialogflow.Dialogflow;

/**
 * @param args
 * 		download -Ddir=<dir> -Dtoken=<token> -DprojectId=<project id>
 * 		upload -Ddir=<dir> -Dtoken=<token> -DprojectId=<project id>
 */
public class DialogflowCi 
{
    public static void main( String[] args ) throws Throwable
    {
    	Options options = new Options();
    	Option projectIdOpt = Option.builder("p")
    			.longOpt("projectId")
				.numberOfArgs(1)
				.valueSeparator()
    			.argName("project ID")
				.desc("The ID of the Dialogflow project")
				.required()
				.build();
		options.addOption(projectIdOpt);
    	
    	Option tokenOpt = Option.builder("t")
    			.longOpt("token")
				.numberOfArgs(1)
				.valueSeparator()
    			.argName("Token")
				.desc("Access token to the Dialogflow project")
				.required()
				.build();
		options.addOption(tokenOpt);
		
		Option dirOpt = Option.builder("d")
    			.longOpt("dir")
				.numberOfArgs(1)
				.valueSeparator()
    			.argName("Directory")
				.desc("The directory from/to where to download/upload")
				.required()
				.valueSeparator()
				.build();
		options.addOption(dirOpt);
		
		Exception e = new IllegalArgumentException("First argument should be 'upload' or 'download'");
		if (args.length == 0)
			throw e;
		
		String command = args[0];
		if (!command.equals("download") && !command.equals("upload"))
			throw e;
    	
    	CommandLineParser parser = new DefaultParser();
    	CommandLine cmd = null;
    	try {
    		cmd = parser.parse(options, Arrays.copyOfRange(args, 1, args.length));
    	} catch(org.apache.commons.cli.MissingOptionException ex) {
    		System.out.println(ex.getMessage());
    		new HelpFormatter().printHelp(command, options);
    		System.exit(1);
    	}
    	
    	String projectId = cmd.getOptionValue(projectIdOpt.getOpt());
		String token = cmd.getOptionValue(tokenOpt.getOpt());
		String dir = cmd.getOptionValue(dirOpt.getOpt());
    	
    	if (command.equals("download")) {
			new Dialogflow(projectId, token).download(dir);
    	} else {
			new Dialogflow(projectId, token).upload(dir);
    	}
    }
}
