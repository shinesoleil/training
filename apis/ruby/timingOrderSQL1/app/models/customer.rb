class Customer < ApplicationRecord
  has_many :orders

  validates :name, :city, presence: true
end
