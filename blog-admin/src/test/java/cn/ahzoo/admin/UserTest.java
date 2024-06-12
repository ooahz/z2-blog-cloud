package cn.ahzoo.admin;

import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void generatePassWord() {
        String plainText = "123456";
        Digester digester = new Digester(DigestAlgorithm.SHA256);
        String salt = HexUtil.encodeHexStr(RandomUtil.randomBytes(11));
        String digestPassword = digester.digestHex(plainText + salt);
        System.out.printf("salt: %s\npassword：%s\n", salt, digestPassword);
    }
}
