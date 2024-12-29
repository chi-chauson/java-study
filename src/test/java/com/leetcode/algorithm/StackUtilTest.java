package com.leetcode.algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackUtilTest {

    private StackUtil pathUtil = new StackUtil(); // Instance of your class

    @Test
    void testSimplifyPath_Example1() {
        assertEquals("/home", pathUtil.simplifyPath("/home/"));
    }

    @Test
    void testSimplifyPath_Example2() {
        assertEquals("/home/foo", pathUtil.simplifyPath("/home//foo/"));
    }

    @Test
    void testSimplifyPath_Example3() {
        assertEquals("/home/user/Pictures", pathUtil.simplifyPath("/home/user/Documents/../Pictures"));
    }

    @Test
    void testSimplifyPath_Example4() {
        assertEquals("/", pathUtil.simplifyPath("/../"));
    }

    @Test
    void testSimplifyPath_Example5() {
        assertEquals("/.../b/d", pathUtil.simplifyPath("/.../a/../b/c/../d/./"));
    }

    @Test
    void testSimplifyPath_EmptyPath() {
        assertEquals("/", pathUtil.simplifyPath(""));
    }

    @Test
    void testSimplifyPath_RootPath() {
        assertEquals("/", pathUtil.simplifyPath("/"));
    }

    @Test
    void testSimplifyPath_MultipleDots() {
        assertEquals("/....", pathUtil.simplifyPath("/..../"));
    }

    @Test
    void testSimplifyPath_PathWithUnderscore() {
        assertEquals("/my_folder", pathUtil.simplifyPath("/my_folder/"));
    }

    @Test
    void testSimplifyPath_ConsecutiveDots() {
        assertEquals("/...", pathUtil.simplifyPath("/hello/./../.../"));
    }

    @Test
    void testSimplifyPath_TrickyDotsAndRelativePaths() {
        assertEquals("/b", pathUtil.simplifyPath("/a/./b/../../c/../b/"));
    }

    @Test
    void testMakeGood1() {
        assertEquals("leetcode", StackUtil.makeGood("leEeetcode"));
    }

    @Test
    void testMakeGood2() {
        assertEquals("", StackUtil.makeGood("abBAcC"));
    }

    @Test
    void testMakeGood3() {
        assertEquals("s", StackUtil.makeGood("s"));
    }

    @Test
    void testMakeGood4() {
        assertEquals("", StackUtil.makeGood("Pp"));
    }

    @Test
    void testMakeGood5() {
        assertEquals("a", StackUtil.makeGood("a"));
    }

    @Test
    void testMakeGood6() {
        assertEquals("b", StackUtil.makeGood("b"));
    }

    @Test
    void testMakeGood7() {
        assertEquals("", StackUtil.makeGood("Aa"));
    }
    @Test
    void testMakeGood8() {
        assertEquals("A", StackUtil.makeGood("A"));
    }
    @Test
    void testMakeGood9() {
        assertEquals("a", StackUtil.makeGood("a"));
    }
    @Test
    void testMakeGoodNull() {
        assertEquals(null, StackUtil.makeGood(null));
    }
    @Test
    void testMakeGoodEmpty() {
        assertEquals("", StackUtil.makeGood(""));
    }

}