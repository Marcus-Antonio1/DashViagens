-- Adiciona campo de imagem aos países (URL do Unsplash)
ALTER TABLE countries ADD COLUMN IF NOT EXISTS image_url VARCHAR(500);

-- Adiciona campo de imagem às atrações (já existe no model mas pode não estar na tabela)
ALTER TABLE attractions ADD COLUMN IF NOT EXISTS image_url VARCHAR(500);
