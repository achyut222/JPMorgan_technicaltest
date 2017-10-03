package com.jpmorgan.technicaltest;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class ReportTransactionTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ReportTransactionTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ReportTransactionTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testReportTransaction()
    {
        assertTrue( true );
    }
}
