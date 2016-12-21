FactoryGirl.define do
  factory :product do
    name "MyString"

    trait :without_name do
      name nil
    end
  end
end
