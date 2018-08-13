# TPO Integración de Aplicaciones - Gestión de Reclamos
# Grupo 6

## Fase Inicial

```cpp
// TODO: Agregar img/fase_inicial.png
```

## Circuito
 - La tienda vende a sus clientes al contado o en cuenta corriente, una vez confirmada la venta se actualiza el stock de los productos vendidos.
 - El cliente puede retirar el producto de la tienda o pactar el envío a domicilio. La tienda debe notificar a Almacen los envios coordinados.
 - El Almacén controla el stock de los productos manejando puntos de pedido o stock mínimo y puntos de reposición. Cuando se alcanza el límite de stock se emiten las correspondientes órdenes de compra para el Distribuidor.
 - El Almacén recibe los envíos a domicilio pactados, separa los productos y prepara la orden de expedición que será enviada a Logística.
 - El Distribuidor vende productos a sus clientes en su tienda o recibiendo órdenes de compra automáticas.
 - El Distribuidor actualiza el stock de su Almacén y pacta envíos a domicilio de forma similar a la Tienda.
 - Logística recibe los productos a enviar junto con la orden de expedición y prepara los envíos.
 - Diariamente Logística prepara las hojas de ruta que los repartidores deberán seguir para entregar los envíos.
 - Tesorería gestiona las cuentas corrientes de los clientes ingresando las novedades diarias de ventas y cobranzas realizadas.
 - Tesorería contrata un servicio tercerizado para cobrar los saldos adeudados a sus clientes. Diariamente emite un listado de clientes y montos a cobrar y lo envía a Gestión Reclamo - Cobranza
 - Tesorería administra los saldos a pagar con los proveedores. Recibe de Almacén las Órdenes de Compra enviadas, las compara con las facturas enviadas por los Distribuidores y realiza los pagos.
 - Gestión Reclamo - Cobranza recibe los reclamos de los clientes. Verifica con la Tienda que el reclamo sea válido y coordina el retiro del producto y la devolución del monto de la compra.
 - Gestión Reclamo - Cobranza recibe de Tesorería el listado de cobranzas a realizar. Envía a sus cobradores a recaudar y luego líquida lo recibido.
 - Gestión Reclamo - Cobranza realiza una nota de devolución con todos los productos retirados y los entrega al Almacén correspondiente.
 - Gestión Reclamo - Cobranza realiza una liquidación a Tesorería de todos los reclamos en los que realizó la devolución de dinero.

## Fase Integracion

```cpp
// TODO: Agregar img/fase_integracion.png
```

## Requerimientos funcionales:
 - El sistema permite recibir reclamos de clientes por compras de productos a tiendas.
 - El sistema permite realizar la devolución de productos mediante el retiro del producto del domicilio del cliente y la devolución del monto pagado.
 - El sistema permite realizar cobranzas tercerizadas.
 