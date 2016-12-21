class Product < ApplicationRecord
  has_many :prices

  validates :name, presence: true

  def route_url
    "products/#{id}"
  end
end
