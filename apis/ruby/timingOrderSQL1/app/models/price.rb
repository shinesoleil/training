class Price < ApplicationRecord
  belongs_to :product

  validates :amount, presence: true

  def route_url
    "products/#{product_id}/prices/#{id}"
  end
end
