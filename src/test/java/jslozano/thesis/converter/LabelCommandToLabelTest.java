package jslozano.thesis.converter;

import jslozano.thesis.command.LabelCommand;
import jslozano.thesis.model.Label;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LabelCommandToLabelTest {
    LabelCommandToLabel converter;
    public static final Long ID_VALUE = 1L;
    public static final String NAME_VALUE = "globant";

    @BeforeEach
    void setUp() {
        converter = new LabelCommandToLabel();
    }
    @Test
    void testNullSource() throws Exception{
        assertNull(converter.convert(null));
    }
    @Test
    void testEmptyObject(){
        assertNotNull(converter.convert(new LabelCommand()));
    }
    @Test
    void testConverter(){
        //given
        LabelCommand labelCommand = new LabelCommand();
        labelCommand.setId(ID_VALUE);
        labelCommand.setName(NAME_VALUE);

        //when
        Label label = converter.convert(labelCommand);

        //then
        assertEquals(ID_VALUE, label.getId());
        assertEquals(NAME_VALUE, label.getName());
    }

}