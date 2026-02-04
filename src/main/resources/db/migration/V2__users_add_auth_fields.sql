ALTER TABLE users
  ADD COLUMN IF NOT EXISTS is_enabled BOOLEAN NOT NULL DEFAULT true;

-- optional but useful
CREATE INDEX IF NOT EXISTS idx_users_email ON users(email);
