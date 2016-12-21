class Customer < ApplicationRecord
  has_many :orders
  validate :name, :city, presence: true
end
