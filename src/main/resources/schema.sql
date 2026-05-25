CREATE TABLE IF NOT EXISTS user_t (
    u_id VARCHAR(50) PRIMARY KEY,
    u_pwd VARCHAR(200) NOT NULL,
    u_role VARCHAR(10) NOT NULL,
    u_addr VARCHAR(100),
    enabled BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS store_t (
    s_id INT AUTO_INCREMENT PRIMARY KEY,
    u_id VARCHAR(50),
    s_name VARCHAR(50) NOT NULL,
    s_cate VARCHAR(50),

    FOREIGN KEY (u_id) REFERENCES user_t(u_id)
);

CREATE TABLE IF NOT EXISTS menu_t (
    m_id INT AUTO_INCREMENT PRIMARY KEY,
    s_id INT NOT NULL,
    m_name VARCHAR(50) NOT NULL,
    m_price INT NOT NULL,

    FOREIGN KEY (s_id) REFERENCES store_t(s_id)
);

CREATE TABLE IF NOT EXISTS order_t (
    o_id INT AUTO_INCREMENT PRIMARY KEY,
    u_id VARCHAR(50) NOT NULL,
    s_id INT NOT NULL,
    o_total BIGINT NOT NULL,
    o_time DATETIME DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (u_id) REFERENCES user_t(u_id),
    FOREIGN KEY (s_id) REFERENCES store_t(s_id)
);

CREATE TABLE IF NOT EXISTS order_item_t (
    oi_id INT AUTO_INCREMENT PRIMARY KEY,
    o_id INT NOT NULL,
    m_id INT NOT NULL,
    m_name VARCHAR(50) NOT NULL,
    m_price INT NOT NULL,
    oi_cnt INT NOT NULL,
    oi_total BIGINT NOT NULL,

    FOREIGN KEY (o_id) REFERENCES order_t(o_id) ON DELETE CASCADE,
    FOREIGN KEY (m_id) REFERENCES menu_t(m_id)
);

CREATE TABLE IF NOT EXISTS history_t (
    h_id INT AUTO_INCREMENT PRIMARY KEY,
    o_id INT NOT NULL,
    s_id INT NOT NULL,
    u_id VARCHAR(50) NOT NULL,

    FOREIGN KEY (o_id) REFERENCES order_t(o_id) ON DELETE CASCADE,
    FOREIGN KEY (s_id) REFERENCES store_t(s_id),
    FOREIGN KEY (u_id) REFERENCES user_t(u_id)
);