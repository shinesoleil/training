class Payment < ApplicationRecord
  belongs_to :order
  validates :amount, presence: true

  def route_url
    @order = Order.find_by(id: order_id)
    "customers/#{order.customer_id}/orders/#{order_id}/payment"
  end
end
