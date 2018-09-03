import { Client } from './client.model';
import { OrderStatus } from './orderStatus.model';
import { Product } from './product.model';

export interface Order {
  id: number;
  client: Client;
  status: OrderStatus;
  product: Product;
}
