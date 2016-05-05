/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leqienglish.util.enumutil;

import com.leqienglish.entity.ContentTypeEnum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zhuleqi
 */
public class EnumUtilTest {

    public EnumUtilTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void testHasEnum() {
        Assert.assertTrue(EnumUtil.hasEnum(ContentTypeEnum.class, "ARTICLE"));
        Assert.assertTrue(!EnumUtil.hasEnum(ContentTypeEnum.class, "ARTICLE12"));
        Assert.assertTrue(!EnumUtil.hasEnum(EnumUtilTest.class, "ARTICLE12"));
    }

}
