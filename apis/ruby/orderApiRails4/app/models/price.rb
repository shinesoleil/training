class Price < ApplicationRecord
  belongs_to :product

  validates :amount, :product, presence: true

  scope :find_of_product, -> (product_id) {where(:product_id => product_id)}

  def route_url
    "products/#{product_id}/prices/#{id}"
  end
end
