class Order < ApplicationRecord
  belongs_to :customer
  has_many :order_items
  has_one :payment

  validates :total_price, presence: true

  scope :find_of_customer, -> (customer_id) {
    where(customer_id: customer_id)
  }

  def route_url
    "customers/#{customer_id}/orders/#{id}"
  end
end
