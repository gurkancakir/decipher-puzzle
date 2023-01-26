package com.luxoft.decipherpuzzle;

import com.luxoft.decipherpuzzle.core.Cipher;
import com.luxoft.decipherpuzzle.core.Decipher;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DecipherPuzzleApplicationTests {

    @Autowired
    private Decipher decipher;

    @Autowired
    private Cipher cipher;

    @ParameterizedTest
    @ValueSource(strings = {
            "xwc-ccc=wcm",
            "xwc+ccc=wcm",
            "hff+hff=pf+pf+pf+hpf",
            "abc-ca=cd;abd+a=abc",
            "wbbw+ewcw=bsxq"
    })
    public void test_decode(String pattern) {
        decipher.decode(pattern);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "xwc-ccc=wcm",
            "xwc+ccc=wcm",
            "hff+hff=pf+pf+pf+hpf",
            "abc-ca=cd;abd+a=abc",
            "wbbw+ewcw=bsxq"
    })
    public void test_decode_with_thread(String pattern) {
        decipher.decodeThread(pattern);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "12+21=33",
            "321-111=210"
    })
    public void test_encode(String pattern) {
        cipher.encode(pattern);
    }

}
