package jslozano.thesis.converter;

import jslozano.thesis.command.LabelCommand;
import jslozano.thesis.model.Label;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LabelToLabelCommandTest {
    LabelToLabelCommand converter;
    public static final Long ID_VALUE = 1L;
    public static final String NAME_VALUE = "globant";

    @BeforeEach
    void setUp() {
        converter = new LabelToLabelCommand();
    }
    @Test
    public void testNullSource() throws Exception{
        assertNull(converter.convert(null));
    }
    @Test
    public void testEmptyObject(){
        assertNotNull(converter.convert(new Label()));
    }
    @Test
    public void testConvert(){
        // given
        Label label = new Label();
        label.setId(ID_VALUE);
        label.setName(NAME_VALUE);

        //when
        LabelCommand labelCommand = converter.convert(label);

        //then
        assertEquals(ID_VALUE, labelCommand.getId());
        assertEquals(NAME_VALUE, labelCommand.getName());
    }
}