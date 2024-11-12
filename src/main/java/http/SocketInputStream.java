package http;

import java.io.IOException;
import java.io.InputStream;

/**
 * 扩展 InputStream 以提高在 HTTP 头处理期间读取行的效率。
 *
 * @author LILINJIAN
 * @since 2023/02/27 15:53
 */
public class SocketInputStream extends InputStream {

    /**
     * 实例化一个新的 Socket 输入流。
     * @param inputStream 从Socket读取的输入流
     * @param size        内部缓冲区大小
     */
    public SocketInputStream(InputStream inputStream, int size) {
        this.inputStream = inputStream;
        buffer = new byte[size];
    }

    /**
     * 从Socket读取的输入流
     */
    protected InputStream inputStream;

    /**
     * 内部缓冲区
     */
    protected byte[] buffer;

    /**
     * 缓冲区中最后一个有效byte的位置
     */
    protected int count;

    /**
     * 缓冲区中的当前位置。
     */
    protected int pos;

    /**
     * 协议中通常包含的字符
     */
    public enum CharacterConstants {
        /**
         * 回车符
         */
        CR((byte) '\r'),

        /**
         * 换行符
         */
        LF((byte) '\n'),

        /**
         * 空白
         */
        SP((byte) ' '),

        /**
         * 水平制表符
         */
        HT((byte) '\t'),

        /**
         * 冒号
         */
        COLON((byte) ':'),

        /**
         * 小写偏移
         */
        LC_OFFSET('A' - 'a');

        private final int value;

        CharacterConstants(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 从输入流中读取字节，将缓冲区填满
     *
     * @throws IOException 如果在读取过程中发生I/O错误
     */
    protected void fill() throws IOException {
        // 重置缓冲区的当前位置指针和有效数据计数
        this.pos = 0;
        this.count = 0;
        // 尝试从输入流中读取数据，填充整个缓冲区
        int filledLength = this.inputStream.
                read(this.buffer, 0, this.buffer.length);
        // 如果成功读取了数据（长度大于0），更新count值
        if (filledLength > 0) {
            this.count = filledLength;
        }
        // 如果filledLength为-1，表示已到达流的末尾，count将保持为0
    }

    /**
     * 从输入流中读取1个字节的数据。
     * <p>
     * 此方法首先检查内部缓冲区是否已耗尽。如果是，它会尝试重新填充缓冲区。
     * 如果在重新填充后仍然没有数据，则表示已到达流的末尾。
     *
     * @return 读取的字节数据（0-255之间的整数），如果到达流的末尾则返回-1
     * @throws IOException 如果发生I/O错误
     */
    @Override
    public int read() throws IOException {
        if (this.pos >= this.count) {
            this.fill();
            if (this.pos >= this.count) {
                return -1;
            }
        }
        return this.buffer[this.pos++] & 0xFF;
    }

    /**
     * 估计可以从输入流中无阻塞读取的字节数。
     *
     * @return 可以无阻塞读取的估计字节数
     * @throws IOException 如果发生I/O错误
     */
    public int available() throws IOException {
        // 计算当前实例中缓冲区中剩余的可读字节数
        int bufferRemaining = this.count - this.pos;
        // 获取底层输入流中可用的字节数
        int underLyingInputStreamAvailable = this.inputStream.available();
        // 返回缓冲区剩余字节数与底层输入流可用字节数之和
        return bufferRemaining + underLyingInputStreamAvailable;
    }

    /**
     * 关闭此输入流并释放与之关联的所有系统资源。
     * 首先检查底层输入流是否已经关闭。如果未关闭，则关闭底层输入流。
     * 关闭后，它会清除对底层输入流和缓冲区的引用，以便垃圾回收。
     *
     * @throws IOException 如果在关闭流时发生I/O错误
     */
    public void close()
            throws IOException {
        if (this.inputStream == null) {
            return;
        }
        this.inputStream.close();
        this.inputStream = null;
        this.buffer = null;
    }
}

//TODO
// 具体解析实现