import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void commandCreatedTest(){
        Parser parser = new Parser();
        assertTrue(parser.parse("ok") instanceof UnmeaningCommand);
        assertTrue(parser.parse("list") instanceof ListCommand);
        assertFalse(parser.parse("listlist") instanceof ListCommand);
        assertTrue(parser.parse("find eizae") instanceof FindCommand);
        assertTrue(parser.parse("done 12") instanceof DoneCommand);
        assertFalse(parser.parse("done okk") instanceof DoneCommand);
        assertTrue(parser.parse("delete 12") instanceof DeleteCommand);
        assertFalse(parser.parse("delete e") instanceof DeleteCommand);
        assertTrue(parser.parse("todo ekzoa") instanceof TodoCommand);
        assertTrue(parser.parse("deadline d1")instanceof DeadlineCommand);
        assertTrue(parser.parse("event e") instanceof EventCommand);
        assertTrue(parser.parse("bye")instanceof ByeCommand);
        assertFalse(parser.parse("bye " )instanceof ByeCommand);
    }

    @Test
    public void stringToDateTest() {
        Parser parser = new Parser();
        Ui ui = new Ui();
        int test = 0;
        try{ // no catch => test = 0
            parser.stringToDate("12/12/1222 22:22",ui);
        }
        catch (InexistentDateException e){
            test++;
        }

        try{ // catch => test = 1
            parser.stringToDate("31/11/1222 22:22",ui);
        }
        catch (InexistentDateException e){
            test++;
        }

        try{ // catch => test =2
            parser.stringToDate("35/12/1222 22:22",ui);
        }
        catch (InexistentDateException e){
            test++;
        }

        try{ // catch => test = 3
            parser.stringToDate("29/02/2021 22:22",ui);
        }
        catch (InexistentDateException e){
            test++;
        }

        try{ // catch => test = 4
            parser.stringToDate("29/13/2021 22:22",ui);
        }
        catch (InexistentDateException e){
            test++;
        }

        try{ // catch => test = 5
            parser.stringToDate("29/12/2021 25:22",ui);
        }
        catch (InexistentDateException e){
            test++;
        }

        try{ // catch => test = 6
            parser.stringToDate("29/12/2021 24:00",ui);
        }
        catch (InexistentDateException e){
            test++;
        }

        try{ // catch => test = 7
            parser.stringToDate("29/12/2021 23:60",ui);
        }
        catch (InexistentDateException e){
            test++;
        }

        try{ // catch => test = 8
            parser.stringToDate("-29/12/2021 23:60",ui);
        }
        catch (InexistentDateException e){
            test++;
        }

        assertEquals(8,test);
    }
}