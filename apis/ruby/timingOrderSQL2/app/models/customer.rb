class Customer < ApplicationRecord
  has_many :orders
  validates :name, :city, presence: true

  def route_url
    "customers/#{id}"
  end
end
