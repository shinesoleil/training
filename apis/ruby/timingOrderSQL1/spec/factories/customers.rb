FactoryGirl.define do
  factory :customer do
    name "MyString"
    city "MyString"

    trait :without_name do
      name nil
    end

    trait :without_city do
      city nil
    end
  end
end
