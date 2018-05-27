package test.file.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.stream.Stream;

/**
 * Generates checksum/hash for files using MessageDigest algorithms. It supports MD5/SHA-1/SHA-256 algorithms. Some
 * times same file can have different checksum value on different platforms because of line terminators or line breaks
 * are represented by different characters depending on OS. For example, in Windows operating system Windows 8 and 10,
 * lines are terminated by \n\r also known as CR+LF, while in Unix environment e.g. Linux or Solaris line terminator is
 * \n, known as line feed LF.
 * 
 * This class generates same checksum/hash value for a file in platform independent way. To run, it requires Java8 and
 * above versions.
 */
public class FileCheckSumGenerator
{

    /**
     * Generates checksum for a given file using MessageDigest algorithms (MD5/SHA-1/SHA-256)
     * 
     * @param file
     *            path of file
     * @return MD5 digest value as a hex string
     * @throws Exception
     */
    public static String hashFile(String file) throws Exception
    {
        String fileContent = readFile(file);
        fileContent = fileContent.replaceAll("\\r\\n|\\r|\\n", " ");

        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.update(fileContent.getBytes(StandardCharsets.UTF_8));
        byte[] hashedBytes = digest.digest();

        return convertByteArrayToHexString(hashedBytes);
    }

    private static String readFile(String filePath)
    {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

    /**
     * Generates hex string from given bytes
     * 
     * @param arrayBytes
     *            byte array, checksum to be calculated
     * @return MD5 checksum
     */
    private static String convertByteArrayToHexString(byte[] arrayBytes)
    {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arrayBytes.length; i++)
        {
            stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return stringBuffer.toString();
    }

}
