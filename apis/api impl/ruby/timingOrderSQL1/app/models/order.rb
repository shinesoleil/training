class Order < ApplicationRecord
  has_many :order_items
  has_one :payment
  belongs_to :customer

  scope :find_of_customer, -> (customer_id) {where(customer_id: customer_id)}
end
