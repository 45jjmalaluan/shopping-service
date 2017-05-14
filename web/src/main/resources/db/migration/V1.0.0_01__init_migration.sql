CREATE TABLE cart (
  id text PRIMARY KEY
);

CREATE TABLE product (
  id text PRIMARY KEY,
  description text NOT NULL,
  price decimal(10,2) NOT NULL
);

CREATE TABLE public.order (
  id text PRIMARY KEY,
  "time" timestamp with time zone NOT NULL,
  total decimal(10,2) NOT NULL
);

CREATE TABLE item (
  id text PRIMARY KEY,
  order_id text,
  product_id text NOT NULL,
  quantity integer NOT NULL,
  price decimal(10,2) NOT NULL,
  cart_id text NOT NULL,
  CONSTRAINT item_order_id_fkey FOREIGN KEY (order_id) REFERENCES public.order (id) ON UPDATE CASCADE,
  CONSTRAINT item_product_id_fkey FOREIGN KEY (product_id) REFERENCES product (id),
  CONSTRAINT item_cart_id_fkey FOREIGN KEY (cart_id) REFERENCES cart (id) ON UPDATE CASCADE
);

