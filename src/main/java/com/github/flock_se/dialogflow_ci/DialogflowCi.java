package com.github.flock_se.dialogflow_ci;

/**
 * @param args
 * 		download <dir> <token> <project id>
 * 		upload <dir> <token> <project id>
 */
public class DialogflowCi 
{
    public static void main( String[] args ) throws Throwable
    {
    	cucumber.api.cli.Main.main(args);
    }
}
